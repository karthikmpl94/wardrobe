package com.ssl.wardrobe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.LineItemModel;

public interface LineItemDao extends PagingAndSortingRepository<LineItemModel, Long> {

	@Query("Select l From LineItemModel l Where l.bitReference=?1")
	public List<LineItemModel> getLineItemsByBitReferenceID(String bitReference);

}
