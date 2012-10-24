
Ext.onReady(function(){
	try {
		if ($('ID')!=null && $('ID').value=='-1')
			alert('ID lost.Not support!)');
		
		if ($('BILL_STATUS'))
			if ($('BILL_STATUS').value=='init'||Hy.isEmpty($('BILL_STATUS').value)) {
			} else {
			}
	}catch(E){}
	var g;
	g=Ext.getCmp('SUBGRID');
	
	var resizer = new Ext.Resizable('SUBGRID', {
		handles: 'e' 
		,resizeChild:true
		,pinned: true
	});
	resizer.on('resize', function() {
		var box = resizer.getEl().getSize();
		this.setSize(box);
		//this.syncSize();
		this.doLayout();
	}, g);
	var month = $("MONTH").value,re=new RegExp("20(\\d\\d)01"),r;
	var lastMonth;
	r=month.match(re);
	if(r){
		lastMonth = "20"+(r[1]-1)+"12";
	}else{
		lastMonth = month-1;
	}
	$("TITLE_L").innerHTML="<div style='text-align:center;font-size:40px;margin:20px 0 20px 0'>&nbsp;供应商月度对账单</div>";
	$("TITLE").parentNode.parentNode.remove();
	$("TITLE_L").setAttribute("colspan",'4');
	$("MONTH_L").innerHTML="<div style='font-size:14px;margin:0px 0px 0px 15px'>月度:"+$("MONTH").value+"</div>";
	$("MONTH").parentNode.parentNode.remove();
	$("SUPPLIER_L").innerHTML="<div style='font-size:14px;margin:0px 0px 0px 15px'>供应商:"+$("__SUPPLIER__NAME").value+"</div>";
	$("__SUPPLIER__NAME").parentNode.parentNode.remove();
	
	$("BEGIN_DATE_L").innerHTML="<div style='font-size:14px;margin:0px 0px 0px 15px'>日期:"+lastMonth+"21"+" 至 "+month+"20</div>";
	$("BEGIN_DATE").parentNode.parentNode.remove();
	

	$("CONTENT").parentNode.parentNode.remove();
//	$("CONTENT_L").innerHTML="<div style='font-size:10px;margin:0px 0px 0px 15px'>我们未能收到贵司上月发票，请您先核对上月发票再开具本月，疑问请致电(xxx)</div>";
	var d = new Date();
	var year = d.getFullYear();
	var month1 = add_zero(d.getMonth()+1);
	var day = add_zero(d.getDate());
	if(""+year+month1+day<month+"20"){
		$("BILL_AMOUNT").parentNode.parentNode.remove();
		$("BILL_AMOUNT_L").innerHTML="<div style='font-size:10px;margin:0px 0px 0px 15px'>待月结帐期到达后，系统将会显示开票金额</div>";
	}
		
});

function add_zero(temp)
{
 if(temp<10) return "0"+temp;
 else return temp;
}
