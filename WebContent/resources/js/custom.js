$(document).ready(function() {

	loadYears();

});

/**
 * Description :  Loading the Year in a drop down
 * @returns
 */
function loadYears() {
	
	$.ajax({
		type : "GET",
		url : "/SydneyTrainService/loadYears",
		dataType : "json",
		success : function(data) {

			$('#yearsDD').empty();
			$('<option value="">-- Select Year --</option>').appendTo(
			'#yearsDD');
			$.each(data.Years, function(i, obj) {

				var div_data = "<option value=" + obj.id + ">" + obj.value
						+ "</option>";
				console.log(div_data);
				$(div_data).appendTo('#yearsDD');
			});

		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

/**
 * Description :  Fetching the Yearly data using ajax
 * @returns
 */
function selectedYear() {
	var selYear = $("#yearsDD option:selected").val();
	console.log(selYear);

	$.ajax({
		type : "GET",
		url : "/SydneyTrainService/yearlyData?year=" + selYear,
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		success : function(data) {

			loadDefaulterTable(data.Offences);
			renderChartNoOfPN(data.Offences);
			renderChartFaceValue(data.Offences);

		},
		error : function(e) {
			$("#errorMsg").html("Data for this Year does not exist..!!");
			console.log("ERROR: ", e);
		}
	});

}

/**
 * Description :  Loading the fetched data in a data table
 * @returns
 */
function loadDefaulterTable(datas) {

	var table = $('#yearlyTable')
			.DataTable(
					{

						destroy : true,
						searching : false,
						paging : false,
						bSort : false,
						bprocessing : true,
						bserverSide : true,
						data : datas,
						fixedHeader : {
							footer : true
						},
						footerCallback : function(row, datas, start, end,
								display) {
							var api = this.api();

							var intVal = function(i) {
								return typeof i === 'string' ? i.replace(
										/[\$,]/g, '') * 1
										: typeof i === 'number' ? i : 0;
							};
							
							//adding all the values of PN column
							var pnTotal = api.column(1).data().reduce(
									function(a, b) {
										return intVal(a) + intVal(b);
									}, 0);

							//adding all the values of Face Value column
							var fvTotal = api.column(2).data().reduce(
									function(a, b) {
										return intVal(a) + intVal(b);
									}, 0);

							//appending the values in the footer
							$(api.column(0).footer()).html('Total');
							$(api.column(1).footer()).html(pnTotal);
							$(api.column(2).footer()).html('$ ' + fvTotal);

						},
						columns : [ {
							data : 'OffenceCategory',
							title : 'Offence Category',
							"sClass" : "centerCol"
						}, {
							data : 'NumberOfPNs',
							title : 'Number of PNs',
							"sClass" : "centerCol"
						}, {
							data : 'FaceValue',
							title : 'Face Value',
							"sClass" : "centerCol"
						}

						],

					});
	table.columns.adjust();
}

/**
 * Description :  Loading the chart to show number of PN against each Offence
 * @returns
 */
function renderChartNoOfPN(datas) {
	var dataPoints = [];
	$.each(datas, function(key, value) {
		dataPoints.push({
			label : value.OffenceCategory,
			y : parseInt(value.NumberOfPNs)
		});
	});

	//console.log(dataPoints);
	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled : true,
		title : {
			text : "Number of PNs / Offence Category",
			fontSize : 15,
		},
		axisX : {
			labelFontSize : 10,
		},
		axisY : {
			labelFontSize : 15,
		},
		dataPointWidth : 40,
		data : [ {
			type : "column",
			dataPoints : dataPoints,
		} ]
	});

	chart.render();

}

/**
 * Description :  Loading the chart to show Face Value against each Offence
 * @returns
 */
function renderChartFaceValue(datas) {
	var dataPoints = [];
	$.each(datas, function(key, value) {
		dataPoints.push({
			label : value.OffenceCategory,
			y : parseInt(value.FaceValue)
		});
	});

	//console.log(dataPoints);
	var chart = new CanvasJS.Chart("chartContainer2", {
		animationEnabled : true,
		title : {
			text : "Face Value / Offence Category",
			fontSize : 15,
		},
		axisX : {
			labelFontSize : 10,
		},
		axisY : {
			labelFontSize : 15,
		},
		dataPointWidth : 40,
		data : [ {
			type : "column",
			dataPoints : dataPoints,
		} ]
	});

	chart.render();

}


$(window).resize(function() {
	  if ($(window).width() <= 800) {
	    $('.leftChartContainer').remove().insertAfter($('.rightChartContainer'));
	  } else {
	    $('.leftChartContainer').remove().insertBefore($('.rightChartContainer'));
	  }
});