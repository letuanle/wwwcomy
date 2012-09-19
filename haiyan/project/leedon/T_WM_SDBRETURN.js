
function renderREM(value, metaData, record, rowIndex, colIndex, store, disVal, colVal, fieldName) {
	if (record.get('CODE').length<6)
		return "";
	return "<button onclick=\"testRem("+rowIndex+");\" type=\"button\" class=\"zbutton1\" >REM</button>";
}
function renderPRE(value, metaData, record, rowIndex, colIndex, store, disVal, colVal, fieldName) {
	return "<button onclick=\"testPre('"+rowIndex+"');\" type=\"button\" class=\"zbutton1\" >PRE</button>";
}

function renderTreeCol(value, metaData, record, rowIndex, colIndex, store, disVal, colVal, fieldName) {
	var parentID=record.get('PARENTID'), isLeaf=record.get('ISLEAF')*1==1, treeCol=record.get(fieldName)  
		,isExpand=false, bufLevel=[]; // special level
	if (!treeCol)
		return value;
	if (Ext.isEmpty(record.data['__isExpand']))
		record.data['__isExpand']=isExpand;
	else
		isExpand=record.data['__isExpand'];
	for (var i=2;i<treeCol.length;i+=2) {
		bufLevel.push('&nbsp;&nbsp;');
	}
	var indent=bufLevel.join(''),
		levelIconTag=['<img src="', Ext.BLANK_IMAGE_URL, 
			'" class="x-tree-ec-icon x-tree-elbow', isLeaf?'':(isExpand?'-minus':'-plus'),
			'" ', isLeaf?'':' style="width:16px;height:18px;background:url(comResource/images/default/tree/elbow'
				+(isExpand?'-minus':'-plus')+'.gif);" ',
			' />'].join(''),
		nodeIconTag=['<img src="', Ext.BLANK_IMAGE_URL, 
			'" class="x-tree-node-icon', ' x-tree-node-inline-icon',
			'" ', isLeaf?'':' style="width:16px;height:18px;background:url(comResource/images/default/tree/folder'
				+(isExpand?'-open':'')+'.gif);" ', 
			' />'].join('');
	var v=['<div class="x-tree-node-el ', 
		isLeaf?'x-tree-node-leaf':(true?'x-tree-node-expanded':'x-tree-node-collapsed'), 
		' x-unselectable " unselectable="on">',
		'<span class="x-tree-node-indent">', indent, "</span>",
		levelIconTag,
		nodeIconTag,
		'<span>', value, "</span></div>"
		].join('');
	return v;
}
Ext.onReady(function(){

	var g;
	g=Ext.getCmp('SUBGRID2');
	
	var resizer = new Ext.Resizable('SUBGRID2', {
		handles: 'e' 
		,resizeChild:true
		,pinned: true
	});
	resizer.on('resize', function() {
		var box = resizer.getEl().getSize();
		this.setSize(box);
		this.doLayout();
	}, g);
});
