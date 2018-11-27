package com.stc.centraldatabase.model.user;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stc.centraldatabase.dao.user.UserSearchDao;




public class LazyUserDataModel extends LazyDataModel<UserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429001188797238226L;
	
	UserSearchData userSearchData;

	public UserSearchData getUserSearchData() {
		return userSearchData;
	}

	public void setUserSearchData(UserSearchData userSearchData) {
		this.userSearchData = userSearchData;
	}
	
	public  LazyUserDataModel(UserSearchData usearch) {
		this.userSearchData = usearch;
	}

	@Override
	public List<UserInfo> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		
		userSearchData.setOffset(first);
		userSearchData.setLimit(pageSize);
		
		UserSearchPaginateData userPaginateData = UserSearchDao.find(userSearchData);
		
		setRowCount(userPaginateData.getCount());
		setPageSize(userSearchData.getLimit());
		
		List<UserInfo> userList = userPaginateData.getUserInfoList();
		
		return userList;
	}

}
