package erp.dao;

import java.util.List;

import erp.dto.Title;

/**
 * @author lenovo
 * 1. Create(insert)
 * 2. Read (select, select where) // 전체 검색과 조건에 따른 검색해서 두개!
 * 3. Update(update)
 * 4. Delete(delete)
 * ArrayList를 쓸 것
 */
public interface TitleDao {
	
	// 모두 추상 method 
	List<Title> selectTitleByAll(); 
	Title selectTitleByNo(Title title); 
	
	int insertTitle(Title title); 
	int updateTitle(Title title); 
	int deleteTitle(int titleNo); 
	
}



