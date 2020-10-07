package entity;

import java.sql.Date;

public class Cos {
private int Slid;
private java.sql.Date FtyOrderDD;//下单日期
private String Business;//业务
private String Fty;//公司
private String Destination;//
private String Mailing_order;//邮件单
private String OrderNo;//系统单
private String Total_quantity;//总数
private String Item;
private String Product_name;//产品名称
private String FILAPO;
private String StyleColor;
private String FactoryPO;//指令单号
private int Qty;//数量
private String Drawing;
private java.sql.Date Confirmation_date;//确认日期
private java.sql.Date DeliveryDD;
private String State;//状态
private String Remark;//备注
private String Document_date;
private String SlDate;//生产日期

public String getSlDate() {
	return SlDate;
}
public void setSlDate(String slDate) {
	SlDate = slDate;
}
public int getSlid() {
	return Slid;
}
public void setSlid(int slid) {
	Slid = slid;
}
public java.sql.Date getFtyOrderDD() {
	return FtyOrderDD;
}
public void setFtyOrderDD(java.sql.Date ftyOrderDD) {
	FtyOrderDD = ftyOrderDD;
}
public String getBusiness() {
	return Business;
}
public void setBusiness(String business) {
	Business = business;
}
public String getFty() {
	return Fty;
}
public void setFty(String fty) {
	Fty = fty;
}
public String getDestination() {
	return Destination;
}
public void setDestination(String destination) {
	Destination = destination;
}
public String getMailing_order() {
	return Mailing_order;
}
public void setMailing_order(String mailing_order) {
	Mailing_order = mailing_order;
}
public String getOrderNo() {
	return OrderNo;
}
public void setOrderNo(String orderNo) {
	OrderNo = orderNo;
}
public String getTotal_quantity() {
	return Total_quantity;
}
public void setTotal_quantity(String total_quantity) {
	Total_quantity = total_quantity;
}
public String getItem() {
	return Item;
}
public void setItem(String item) {
	Item = item;
}
public String getProduct_name() {
	return Product_name;
}
public void setProduct_name(String product_name) {
	Product_name = product_name;
}
public String getFILAPO() {
	return FILAPO;
}
public void setFILAPO(String fILAPO) {
	FILAPO = fILAPO;
}
public String getStyleColor() {
	return StyleColor;
}
public void setStyleColor(String styleColor) {
	StyleColor = styleColor;
}
public String getFactoryPO() {
	return FactoryPO;
}
public void setFactoryPO(String factoryPO) {
	FactoryPO = factoryPO;
}
public int getQty() {
	return Qty;
}
public void setQty(int qty) {
	Qty = qty;
}
public String getDrawing() {
	return Drawing;
}
public void setDrawing(String drawing) {
	Drawing = drawing;
}
public java.sql.Date getConfirmation_date() {
	return Confirmation_date;
}
public void setConfirmation_date(java.sql.Date confirmation_date) {
	Confirmation_date = confirmation_date;
}
public java.sql.Date getDeliveryDD() {
	return DeliveryDD;
}
public void setDeliveryDD(java.sql.Date deliveryDD) {
	DeliveryDD = deliveryDD;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}
public String getDocument_date() {
	return Document_date;
}
public void setDocument_date(String document_date) {
	Document_date = document_date;
}



@Override
public String toString() {
	return "Cos [Slid=" + Slid + ", FtyOrderDD=" + FtyOrderDD + ", Business=" + Business + ", Fty=" + Fty
			+ ", Destination=" + Destination + ", Mailing_order=" + Mailing_order + ", OrderNo=" + OrderNo
			+ ", Total_quantity=" + Total_quantity + ", Item=" + Item + ", Product_name=" + Product_name + ", FILAPO="
			+ FILAPO + ", StyleColor=" + StyleColor + ", FactoryPO=" + FactoryPO + ", Qty=" + Qty + ", Drawing="
			+ Drawing + ", Confirmation_date=" + Confirmation_date + ", DeliveryDD=" + DeliveryDD + ", State=" + State
			+ ", Remark=" + Remark + ", Document_date=" + Document_date + ", SlDate=" + SlDate + "]";
}
public Cos(int slid) {
	super();
	Slid = slid;
}
public Cos() {
}
public Cos(int slid, String state) {
	super();
	Slid = slid;
	State = state;
}
public Cos(int slid, String state,String slDate) {
	super();
	Slid = slid;
	State = state;
	SlDate = slDate;
}
public Cos(int slid, Date ftyOrderDD, String business, String fty, String destination, String mailing_order,
		String orderNo, String total_quantity, String item, String product_name, String fILAPO, String styleColor,
		String factoryPO, int qty, String drawing, java.sql.Date confirmation_date, Date deliveryDD, String state,
		String remark, String document_date) {
	super();
	Slid = slid;
	FtyOrderDD = ftyOrderDD;
	Business = business;
	Fty = fty;
	Destination = destination;
	Mailing_order = mailing_order;
	OrderNo = orderNo;
	Total_quantity = total_quantity;
	Item = item;
	Product_name = product_name;
	FILAPO = fILAPO;
	StyleColor = styleColor;
	FactoryPO = factoryPO;
	Qty = qty;
	Drawing = drawing;
	Confirmation_date = confirmation_date;
	DeliveryDD = deliveryDD;
	State = state;
	Remark = remark;
	Document_date = document_date;
}
public Cos(int slid, Date ftyOrderDD, String business, String fty, String mailing_order,
		String orderNo, String item, String product_name, String fILAPO, String styleColor,
		String factoryPO, int qty ,String remark,Date confirmation_date,Date deliveryDD, String state) {
	super();
	Slid = slid;
	FtyOrderDD = ftyOrderDD;
	Business = business;
	Fty = fty;
	Mailing_order = mailing_order;
	OrderNo = orderNo;
	Item = item;
	Product_name = product_name;
	FILAPO = fILAPO;
	StyleColor = styleColor;
	FactoryPO = factoryPO;
	Qty = qty;
	Remark = remark;
	Confirmation_date=confirmation_date;
	DeliveryDD = deliveryDD;
	State = state;

}
public Cos(int slid, String business, String fty, String mailing_order,
		String orderNo, String item, String product_name, String fILAPO, String styleColor,
		String factoryPO, int qty ,String remark, String state, String slDate) {
	super();
	Slid = slid;
	Business = business;
	Fty = fty;
	Mailing_order = mailing_order;
	OrderNo = orderNo;
	Item = item;
	Product_name = product_name;
	FILAPO = fILAPO;
	StyleColor = styleColor;
	FactoryPO = factoryPO;
	Qty = qty;
	Remark = remark;
	State = state;
	SlDate=slDate;

}
}
