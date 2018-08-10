Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.form.*', 'Ext.layout.container.Column', 'Ext.tab.Panel']);
Ext.Loader.setConfig({
    enabled: true
});
Ext.tip.QuickTipManager.init();



var webColumns=[
         			{
         				header : 'Elective Name',
         				dataIndex : 'electiveName',
         				sortable:true,
         				width    :250
         			},
         			{
         				header : 'Center of Gravity',
         				dataIndex : 'cog',
         				sortable:true,
         				width    :100
         			}
         			];

var hideConfirmationMsg;
var showConfirmationMsg;
/* Hide the Confirmation Message */
	hideConfirmationMsg = function() {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML = "";
		confMsgDiv.dom.style.display = 'none';
	}
	/* Show Confirmation Message */
	showConfirmationMsg = function(msg) {
		var confMsgDiv = Ext.get('confirmationMessage');
		confMsgDiv.dom.innerHTML =  msg;
		confMsgDiv.dom.style.display = 'inline-block';		
	}
	var webStore;
Ext.onReady(function () {

	var loadMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading"});
	loadMask.show();
	
	Ext.define('webModel',{
		extend : 'Ext.data.Model',
		fields : [ 
		          	{name:'electiveName', mapping:'electiveName',type:'string'},
		          	{name:'cog',mapping:'cog',type:'double'},
				    {name:'userId',mapping:'userId',type:'string'}
		          ]
		
	});

	webStore = Ext.create('Ext.data.Store', {
		id : 'webSiteStoreId',
		name : 'webSiteStoreName',
		model : 'webModel',
		proxy : {
			type : 'ajax',
			url :contextPath+'/elective/recommendationsList.do',
			extraParams:{
			},
			actionMethods:{
				read:'POST'
			},
			reader : {
				type :'json',
				root:'model'
					}
		},
		listeners:
		{
			'load':function(store, records){
						
				loadMask.hide();
			}
		},
		autoLoad : true
	});
	
	
	Ext.create('Ext.chart.Chart', {
	    renderTo: Ext.getBody(),
	    width: 500,
	    height: 300,
	    animate: true,
	    store: webStore,
	    axes: [{
	        type: 'Numeric',
	        position: 'bottom',
	        fields: ['cog'],
	        label: {
	            renderer: Ext.util.Format.numberRenderer('0,0')
	        },
	        title: 'Center of Gravity',
	        grid: true,
	        minimum: 0
	    }, {
	        type: 'Category',
	        position: 'left',
	        fields: ['electiveName'],
	        title: 'Elective Name'
	    }],
	    series: [{
	        type: 'bar',
	        axis: 'bottom',
	        highlight: true,
	        tips: {
	          trackMouse: true,
	          width: 140,
	          height: 28,
	          renderer: function(storeItem, item) {
	            this.setTitle(storeItem.get('electiveName') + ': ' + storeItem.get('cog') + ' views');
	          }
	        },
	        label: {
	          display: 'insideEnd',
	            field: 'data',
	            renderer: Ext.util.Format.numberRenderer('0'),
	            orientation: 'horizontal',
	            color: '#333',
	            'text-anchor': 'middle'
	        },
		 xField: 'electiveName',
	        yField: 'cog'
		}]
	});
	
	
	

});
	
	
	
