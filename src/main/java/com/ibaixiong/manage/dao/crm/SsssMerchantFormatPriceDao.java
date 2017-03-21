package com.ibaixiong.manage.dao.crm;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SsssMerchantFormatPrice;

public interface SsssMerchantFormatPriceDao {
    
    SsssMerchantFormatPrice selectByPrimaryKey(Long id);
    
    int insertSelective(SsssMerchantFormatPrice record);

	int updateByPrimaryKeySelective(SsssMerchantFormatPrice record);
    
    /**
     * 查询不同规格不同承运商的价格
     * @param formatId		规格ID
     * @param merchantId	承运商ID
     * @return
     */
    SsssMerchantFormatPrice getSsssMerchantFormatPriceByFormatIdAndMerchantId(@Param("formatId")Long formatId,@Param("merchantId")Long merchantId);
    /**
     * 查询不同规格4S店的价格
     * @param formatId		规格ID
     * @param ssssId		4S店ID
     * @return
     */
    SsssMerchantFormatPrice getSsssFormatPriceByFormatIdAndssssId(@Param("formatId")Long formatId,@Param("ssssId")Long ssssId);

}