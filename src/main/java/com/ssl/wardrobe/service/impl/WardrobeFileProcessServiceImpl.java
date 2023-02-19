package com.ssl.wardrobe.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssl.wardrobe.apientities.GetTransationListResponse;
import com.ssl.wardrobe.dao.HeaderDao;
import com.ssl.wardrobe.dao.TempHeaderDao;
import com.ssl.wardrobe.dao.TempLineItemDao;
import com.ssl.wardrobe.dao.TempPaymentDetailDao;
import com.ssl.wardrobe.model.HeaderModel;
import com.ssl.wardrobe.model.TempHeaderModel;
import com.ssl.wardrobe.service.GravityResponseService;
import com.ssl.wardrobe.service.WardrobeEmailService;
import com.ssl.wardrobe.service.WardrobeFileProcessService;
import com.ssl.wardrobe.utils.ProcessFileDetails;
import com.ssl.wardrobe.utils.UtilsPopulator;

@Service
public class WardrobeFileProcessServiceImpl implements WardrobeFileProcessService {

	private static final Logger LOG = LoggerFactory.getLogger(WardrobeFileProcessServiceImpl.class);

	private static final String SLASH = "/";
	private static final String CSV = "csv";
	private static final String GRAVITYFOLDER = "gravty/";
	private static final String DELIMITER=",";

	@Value("${wardrobe.list.of.bitReferences}")
	private String bitReferences;
	@Value("${wardrobe.headerfile.location}")
	private String headerFileLocation;
	@Value("${wardrobe.lineitemfile.location}")
	private String lineItemFileLocation;
	@Value("${wardrobe.paymentdetailfile.location}")
	private String paymentDetailFileLocation;
	@Value("${wardrobe.noOfRecords.header.to.processed}")
	private int maxRecords;

	// private String

	@Autowired
	private ProcessFileDetails processFileDetails;

	@Autowired
	private TempLineItemDao tempLineItemDao;

	@Autowired
	private TempPaymentDetailDao tempPaymentDetailDao;

	@Autowired
	private TempHeaderDao tempHeaderDao;

	@Autowired
	private UtilsPopulator populator;

	@Autowired
	private HeaderDao headerDao;

	@Autowired
	private WardrobeEmailService emailService;
	
	@Autowired
	GravityResponseService gravityResponseService;
	

	private String getSuffixExecutionPath() {
		Calendar calender = Calendar.getInstance();
		return (String.valueOf(calender.get(Calendar.YEAR)) + SLASH
				+ String.format("%02d", calender.get(Calendar.MONTH) + 1) + SLASH
				+ String.format("%02d", calender.get(Calendar.DAY_OF_MONTH)) + SLASH + GRAVITYFOLDER);

	}

	@Override
	@Scheduled(cron = "${wardrobe.header.trigger}")
	public void processHeaderDataFile() {
		File folder = new File(headerFileLocation + this.getSuffixExecutionPath());
		for (File file : folder.listFiles()) {
			if (file.isFile() && FilenameUtils.isExtension(file.getName(), CSV)) {
				LOG.info("Processing header file -->{}", file.getName());
				try {
					processFileDetails.headerFileProcessing(file);
				} catch (Exception e) {
					emailService.sendEmail("Unable to process header file(" + file.getName() + ")");
				}

			}
		}

	}

	@Override
	@Scheduled(cron = "${wardrobe.lineitem.trigger}")
	public void processLineItemsDataFile() {
		File folder = new File(lineItemFileLocation + this.getSuffixExecutionPath());
		for (File file : folder.listFiles()) {
			if (file.isFile() && FilenameUtils.isExtension(file.getName(), CSV)) {
				LOG.info("Processing lineitem file -->{}", file.getName());
				try {
					processFileDetails.lineItemFileProcessing(file);
				} catch (Exception e) {
					emailService.sendEmail("Unable to process lineitem file(" + file.getName() + ")");
				}

			}
		}
	}

	@Override
	@Scheduled(cron = "${wardrobe.paymentdetails.trigger}")
	public void processPaymentTranactionDataFile() {

		File folder = new File(paymentDetailFileLocation + this.getSuffixExecutionPath());
		for (File file : folder.listFiles()) {
			if (file.isFile() && FilenameUtils.isExtension(file.getName(), CSV)) {
				LOG.info("Processing payment file -->{}", file.getName());
				try {
					processFileDetails.paymentDetailFileProcessing(file);
				} catch (Exception e) {
					emailService.sendEmail("Unable to process paymentDetail file(" + file.getName() + ")");
				}

			}
		}
	}

	@Transactional
	@Scheduled(cron = "${wardrobe.main.trigger}")
	public void processTemporaryTablesData() {
		LOG.info("Processing temp tables data-start");
		int pagesCount = tempHeaderDao.getCountOfHeader(this.getBitreferenceId()) / maxRecords;
		Pageable pageable = null;

		for (int page = 0; page <= pagesCount; page++) {
			List<HeaderModel> headerList = new ArrayList<>();
			pageable = PageRequest.of(page, maxRecords);
			List<TempHeaderModel> tempHeaderList = tempHeaderDao.getTempHeaderData(this.getBitreferenceId(), pageable);
			tempHeaderList.stream().forEach(source -> {
				HeaderModel target = new HeaderModel();
				populator.populateHeader(source, target);
				headerList.add(target);
			});
			headerDao.saveAll(headerList);

		}
		LOG.info("Processing temp tables data-ends");
		this.deleteDataFromAllTempTables();
	}

	
	public List<String> getBitreferenceId() {
		return Stream.of(bitReferences.split(DELIMITER)).map(ele -> new String(ele)).collect(Collectors.toList());

	}

	@Transactional
	public void deleteDataFromAllTempTables() {
		tempHeaderDao.deleteTempHeaderTableData();
		tempLineItemDao.deleteTempLineItemTableData();
		tempPaymentDetailDao.deleteTempPaymentDetailTableData();
		LOG.info("Data in temp tables is deleted..!");
	}

	

}
