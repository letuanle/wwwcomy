
function renderCheckboxSelectionModel(v, meta, record, rowIndex, i, store) {
	if (store.treeField && record.get('CODE').length<6)
		return '';
    return '<div class="x-grid3-row-checker">&#160;</div>';
}

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
	$("TITLE_L").innerHTML="<div style='text-align:center;font-size:40px;margin:10px 0 10px 0'>&nbsp;供应商月度对账单</div>";
	$("TITLE").parentNode.parentNode.remove()
	$("TITLE_L").setAttribute("colspan",'4')
	$("CONTENT").parentNode.parentNode.remove()
});
