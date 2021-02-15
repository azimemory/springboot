package com.kh.bookmanager.common.idgenerator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class IdGenerator extends SequenceStyleGenerator{

	public static final String SEQ_PREFIX = "seqPrefix";
	private String seqPrefix;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return seqPrefix + super.getOptimizer().generate( super.getDatabaseStructure().buildCallback(session));
	}
}
