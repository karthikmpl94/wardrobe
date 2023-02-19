package com.ssl.wardrobe.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.TempLineItemModel;

public interface TempLineItemDao extends PagingAndSortingRepository<TempLineItemModel, Long> {

	@Query("Select l From TempLineItemModel l Where l.bitReference=?1")
	public List<TempLineItemModel> getLineItemsByBitReferenceID(String bitReference);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TempLineItemModel")
	public void deleteTempLineItemTableData();
}
