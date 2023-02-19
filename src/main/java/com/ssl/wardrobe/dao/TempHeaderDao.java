package com.ssl.wardrobe.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.TempHeaderModel;


public interface TempHeaderDao extends PagingAndSortingRepository<TempHeaderModel, Long> {

	@Query("Select t from TempHeaderModel t where t.bitReference=?1")
	public TempHeaderModel findHeaderByBitReferenceId(String bitreference);
	
	@Query("Select t from TempHeaderModel t where t.bitType in ?1")
	public List<TempHeaderModel> getTempHeaderData(List<String> bitReferences, Pageable pageable);

	@Query("Select count(t) from TempHeaderModel t where t.bitType in ?1")
	public int getCountOfHeader(List<String> bitReferences);

	@Transactional
	@Modifying      
    @Query(value = "DELETE FROM TempHeaderModel")       
    public void deleteTempHeaderTableData();
	

}
