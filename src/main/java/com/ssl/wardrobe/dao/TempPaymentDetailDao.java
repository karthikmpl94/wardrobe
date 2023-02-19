package com.ssl.wardrobe.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.TempPaymentDetailModel;

public interface TempPaymentDetailDao extends PagingAndSortingRepository<TempPaymentDetailModel, Long> {
	@Query("Select p FROM TempPaymentDetailModel p WHERE p.bitReference=?1")
	public List<TempPaymentDetailModel> getPaymentDetailsByBitReferenceID(String bitReference);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TempPaymentDetailModel")
	public void deleteTempPaymentDetailTableData();
}
