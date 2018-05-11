<div class='mainContent'>
	<div class='centredDiv'>
		<label> Select Year :: </label> <select id="yearsDD"
			onchange="selectedYear()"></select>
	</div>
	<div id="errorMsg" class="errClass"></div>

	<div id="YearlyDatatable">
		<table id="yearlyTable" class="display nowrap">

			<tfoot>
				<tr>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</tfoot>
		</table>
	</div>

	<%-- Chart to show Number of PNs for each Offence Category --%>
	<div id="chartContainer" class="leftChartContainer"></div>

	<%-- Chart to show Face Value for each Offence Category --%>
	<div id="chartContainer2" class="rightChartContainer"></div>


</div>