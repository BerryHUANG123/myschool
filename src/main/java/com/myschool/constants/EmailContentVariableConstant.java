package com.myschool.constants;

/**
 * @author :sujinpeng
 * @Date :2018/2/10
 * @Time :16:54
 * @Description :邮件内容中变量的常量类
 */
public class EmailContentVariableConstant {

    /**
     * 买家名称
     */
    public static final String BUYER_NAME = "{buyerName}";

    /**
     * 买家邮箱
     */
    public static final String BUYER_EMAIL = "{buyerEmail}";

    /**
     * 产品名称
     */
    public static final String PRODUCT_NAME = "{productName}";

    /**
     * 店铺名称
     */
    public static final String SHOP_NAME = "{shopName}";

    /**
     * 订单号
     */
    public static final String ORDER_ID = "{orderId}";

    /**
     * SKU
     */
    public static final String SELLER_SKU = "{sellerSku}";

    /**
     * asin
     */
    public static final String ASIN = "{asin}";

    /**
     * 订单日期
     */
    public static final String ORDER_DATE = "{orderDate}";

    /**
     * 产品数量
     */
    public static final String QUANTITY_ORDERED = "{quantityOrdered}";

    /**
     * 订单总价
     */
    public static final String AMOUNT_TOTAL = "{amountTotal}";

    /**
     * 收货地址1
     */
    public static final String ADDRESS_LINE_1 = "{addressLine1}";

    /**
     * 收货地址2
     */
    public static final String ADDRESS_LINE_2 = "{addressLine2}";

    /**
     * 收货地所在州/地区
     */
    public static final String DISTRICT = "{district}";

    /**
     * 收货地所在城市
     */
    public static final String CITY = "{city}";

    /**
     * 邮政编码
     */
    public static final String POSTAL_CODE = "{postalCode}";

    /**
     * 收货人姓名
     */
    public static final String RECEIVER_NAME = "{receiverName}";

    /**
     * 物流商名称
     */
    public static final String LOGISTICS_DEALER_NAME = "{logisticsDealerName}";

    /**
     * 预计到达时间
     */
    public static final String FORECAST_DELIVER_TIME = "{forecastDeliverTime}";

    /**
     * feedback留评链接
     */
    public static final String FEEDBACK_LINK = "{feedbackLink}";

    /**
     * review留评链接
     */
    public static final String REVIEW_LINK = "{reviewLink}";

    /**
     * 联系卖家链接
     */
    public static final String SELLER_LINK = "{sellerLink}";

    /**
     * 订单链接
     */
    public static final String ORDER_LINK = "{orderLink}";

    /**
     * 店铺链接
     */
    public static final String SHOP_LINK = "{shopLink}";

    /**
     * 产品详情链接
     */
    public static final String PRODUCT_LINK = "{productLink}";

    public static void main(String arg[]){
        String s = "ab{feedbackLink}cd{feedbackLink}";
        if(s.contains(EmailContentVariableConstant.FEEDBACK_LINK)){
            s = s.replace(EmailContentVariableConstant.FEEDBACK_LINK,"");
            System.out.println(s);
        }
    }
}
