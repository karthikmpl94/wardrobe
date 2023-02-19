package com.ssl.wardrobe.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ssl.wardrobe.model.MemberDetailsModel;

public interface MemberDetailsDao extends PagingAndSortingRepository<MemberDetailsModel, Long> {

	@Query("Select count(md) from MemberDetailsModel md where md.isProcessed ='0' order by creationTime")
	public int getCountMemberDetails();

	@Query("Select md from MemberDetailsModel md where md.isProcessed ='0'")
	public List<MemberDetailsModel> getMemberDetails(Pageable pageable);

	@Query("Select md from MemberDetailsModel md where md.memberId=?1")
	public List<MemberDetailsModel> getMemberDetailsByMemberID(String member_id);
}
