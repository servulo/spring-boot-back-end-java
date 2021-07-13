package br.com.sprj.backend.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sprj.backend.dto.ShopReportDTO;
import br.com.sprj.backend.model.Shop;

public class ReportRepositoryImpl implements ReportRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Shop> getShopByFilters(Date beginDate, Date endDate, Float minValue) {

		StringBuilder sb = new StringBuilder();
		sb.append("select s ");
		sb.append("from shop s ");
		sb.append("where s.date >= : beginDate ");

		if (endDate != null) {
			sb.append("and s.total <= :endDate ");
		}

		if (minValue != null) {
			sb.append("and s.total <= : minValue ");
		}

		Query query = entityManager.createQuery(sb.toString());

		query.setParameter("beginDate", beginDate);

		if (endDate != null) {
			query.setParameter("endDate", endDate);
		}

		if (minValue != null) {
			query.setParameter("minValue", minValue);
		}

		return query.getResultList();

	}

	@Override
	public ShopReportDTO getReportByDate(Date beginDate, Date endDate) {

		StringBuilder sb = new StringBuilder();
		sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
		sb.append("from shopping.shop s ");
		sb.append("where sp.date >= : beginDate ");
		sb.append("and sp.date <= :endDate ");

		Query query = entityManager.createNamedQuery(sb.toString());
		query.setParameter("beginDate", beginDate);
		query.setParameter("endDate", endDate);

		Object[] result = (Object[]) query.getSingleResult();

		ShopReportDTO shopReportDTO = new ShopReportDTO();
		shopReportDTO.setCount(((BigInteger) result[0]).intValue());
		shopReportDTO.setTotal((Double) result[1]);
		shopReportDTO.setMean((Double) result[2]);

		return shopReportDTO;

	}

}
