package com.ibaixiong.manage.service.crm;

import com.ibaixiong.entity.SsssMerchantFormatPrice;

public interface SsssMerchantFormatPriceService {
	 /**
     * 查询不同规格不同承运商的价格
     * @param formatId		规格ID
     * @param merchantId	承运商ID
     * @return
     */
    SsssMerchantFormatPrice getSsssMerchantFormatPriceByFormatIdAndMerchantId(Long formatId,Long merchantId);
    /**
     * 查询不同规格4S店的价格
     * @param formatId		规格ID
     * @param ssssId		4S店ID
     * @return
     */
    SsssMerchantFormatPrice getSsssFormatPriceByFormatIdAndssssId(Long formatId,Long ssssId);
    
    void insert(SsssMerchantFormatPrice bean);
    
    void update(SsssMerchantFormatPrice bean);

}
