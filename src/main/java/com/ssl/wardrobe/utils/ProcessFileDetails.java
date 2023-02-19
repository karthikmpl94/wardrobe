package com.ssl.wardrobe.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.ssl.wardrobe.dao.TempHeaderDao;
import com.ssl.wardrobe.dao.TempLineItemDao;
import com.ssl.wardrobe.dao.TempPaymentDetailDao;
import com.ssl.wardrobe.model.TempHeaderModel;
import com.ssl.wardrobe.model.TempLineItemModel;
import com.ssl.wardrobe.model.TempPaymentDetailModel;

@Component
public class ProcessFileDetails {
	private static final Logger LOG = LoggerFactory.getLogger(ProcessFileDetails.class);

	private static final String SEPERATOR = "\\|";

	@Value("${wardrobe.headerfile.header}")
	private String header;

	@Value("${wardrobe.lineitemfile.header}")
	private String lineItemHeader;

	@Value("${wardrobe.paymentdetailfile.header}")
	private String paymentdetailHeader;
	
	@Value("${wardrobe.tempfile.record.to.persist}")
	private int maxRecords;

	@Autowired
	private TempPopulator tempPopulator;

	@Autowired
	private TempHeaderDao tempHeaderDao;

	@Autowired
	private TempLineItemDao tempLineItemDao;

	@Autowired
	private TempPaymentDetailDao tempPaymentDetailDao;

	public void headerFileProcessing(final File file) {
		long record = 0;
		List<TempHeaderModel> tempHeaderList = new ArrayList<>();
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build()) {
			Iterator<String[]> csvIterator = csvReader.iterator();
			while (csvIterator.hasNext()) {
				String line = csvIterator.next()[0];
				if (record == 0 && !header.equals(line)) {
					LOG.info("Header file not processed because of file heading issue.");
					break;
				} else {
					if (record == 0) {
						record++;
						continue;
					}

					TempHeaderModel tempHeaderModel = new TempHeaderModel();
					tempPopulator.populateTempHeader(line.split(SEPERATOR, -1), tempHeaderModel, tempHeaderList);
					if (tempHeaderList.size() == 10000) {
						tempHeaderDao.saveAll(tempHeaderList);
						tempHeaderList.clear();
					}
				}
			}

		} catch (IOException e) {
			LOG.error("Unable to process header data file {}", e);
		}

		if (CollectionUtils.isNotEmpty(tempHeaderList)) {
			tempHeaderDao.saveAll(tempHeaderList);

		}
	}

	public void lineItemFileProcessing(final File file) {
		long record = 0;
		List<TempLineItemModel> tempLineItemList = new ArrayList<>();
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build()) {
			Iterator<String[]> csvIterator = csvReader.iterator();
			while (csvIterator.hasNext()) {
				String line = csvIterator.next()[0];
				if (record == 0 && !lineItemHeader.equals(line)) {
					LOG.info("Lineitem file not processed because of file heading issue.");
					break;
				} else {
					if (record == 0) {
						record++;
						continue;
					}
					TempLineItemModel tempLineItemModel = new TempLineItemModel();
					tempPopulator.populateTempLineItem(line.split(SEPERATOR, -1), tempLineItemModel, tempLineItemList);
					if (tempLineItemList.size() == 10000) {
						tempLineItemDao.saveAll(tempLineItemList);
						tempLineItemList.clear();

					}
				}
			}

		} catch (IOException e) {
			LOG.error("Unable to process lineitem data file {}", e);
		}

		if (CollectionUtils.isNotEmpty(tempLineItemList)) {
			tempLineItemDao.saveAll(tempLineItemList);
		}
	}

	public void paymentDetailFileProcessing(final File file) {
		long record = 0;
		List<TempPaymentDetailModel> paymentDetailList = new ArrayList<>();
		try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).build()) {
			Iterator<String[]> csvIterator = csvReader.iterator();
			while (csvIterator.hasNext()) {
				String line = csvIterator.next()[0];
				if (record == 0 && !paymentdetailHeader.equals(line)) {
					LOG.info("Paymentdetails file not processed because of file heading issue.");
					break;
				} else {
					if (record == 0) {
						record++;
						continue;
					}
					TempPaymentDetailModel tempPaymentDetailModel = new TempPaymentDetailModel();
					tempPopulator.populateTempPaymentDetail(line.split(SEPERATOR, -1), tempPaymentDetailModel,
							paymentDetailList);
					if (paymentDetailList.size() == 10000) {
						tempPaymentDetailDao.saveAll(paymentDetailList);
						paymentDetailList.clear();
					}
				}
			}

		} catch (IOException e) {
			LOG.error("Unable to process lineitem data file {}", e);
		}

		if (CollectionUtils.isNotEmpty(paymentDetailList)) {
			tempPaymentDetailDao.saveAll(paymentDetailList);

		}
	}

}
