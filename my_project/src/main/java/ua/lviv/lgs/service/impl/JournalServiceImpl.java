  
package ua.lviv.lgs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.JournalDao;
import ua.lviv.lgs.dao.impl.JournalDaoImpl;
import ua.lviv.lgs.domain.Journal;
import ua.lviv.lgs.service.JournalService;

public class JournalServiceImpl implements JournalService {

	private static Logger LOGGER = Logger.getLogger(JournalServiceImpl.class);
	private static JournalService journalServiceImpl;
	
	private JournalDao journalDao;

	private JournalServiceImpl() {
		try {
			journalDao = new JournalDaoImpl();
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	
	public static JournalService getJournalService() {
		if (journalServiceImpl == null) {
			journalServiceImpl = new JournalServiceImpl();
		}
		return journalServiceImpl;
	} 

	@Override
	public Journal create(Journal t) {
		return journalDao.create(t);
	}

	@Override
	public Journal read(int id) {
		return journalDao.read(id);
	}

	@Override
	public Journal update(Journal t) {
		return journalDao.update(t);
	}

	@Override
	public void delete(int id) {
		journalDao.delete(id);
	}

	@Override
	public List<Journal> readAll() {
		return journalDao.readAll();
	}

}