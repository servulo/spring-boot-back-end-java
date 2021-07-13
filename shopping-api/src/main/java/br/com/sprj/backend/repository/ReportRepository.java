package br.com.sprj.backend.repository;

import java.util.Date;
import java.util.List;

import br.com.sprj.backend.dto.ShopReportDTO;
import br.com.sprj.backend.model.Shop;

public interface ReportRepository {
	
	public List<Shop> getShopByFilters(Date beginDate, Date endDate, Float minValue);
	
	public ShopReportDTO getReportByDate(Date beginDate, Date endDate);

}
