package dao;

import java.sql.Date;

import mapper.VisitMapper;

public class VisitDAO {
  
	public void insertVisit(int mnum, int onum,int asnum, String visitDate) throws Exception {

		Date vdate = Date.valueOf(visitDate);
		new VisitMapper().insertVisit(mnum,onum,asnum,vdate);
	}
}
