package org.zerock.myapp.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT current_date FROM dual")
	public abstract Date getNow();

}
