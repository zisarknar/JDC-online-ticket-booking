/**
 * @author Robert Wilk
 * Created on 1/20/2016.
 */

// Begin Something modal population and submit functions
var busUrl = "/bus/";
var busEditModalTarget = busUrl + "loadEntity/";
var busTableTarget = busUrl + "loadBusTable/";

var busTypeUrl = "/bustype/";
var busTypeEditModalTarget = busTypeUrl + "loadEntity/";

var servicesUrl = "/services/";
var servicesEditModalTarget = servicesUrl + "loadEntity/";

var stationUrl = "/station/";
var stationEditModalTarget = stationUrl + "loadEntity/";

var addressUrl = "/address/";
var addressEditModalTarget = addressUrl + "loadEntity/";

var citiesUrl = "/cities/";
var citiesEditModalTarget = citiesUrl + "loadEntity/";

// Build the url for the Ajax request for Something.
function busShowEditModal(index) {
    var busEditUrl = busEditModalTarget + index;
    busLoadEntity(busEditUrl);
}

function busTypeShowEditModal(index) {
	var busTypeEditUrl = busTypeEditModalTarget + index;
	busTypeLoadEntity(busTypeEditUrl);
}

function servicesShowEditModal(index) {
	var servicesEditUrl = servicesEditModalTarget + index;
	servicesLoadEntity(servicesEditUrl);
}

function stationShowEditModal(index) {
	var stationEditUrl = stationEditModalTarget + index;
	stationLoadEntity(stationEditUrl);
}

function addressShowEditModal(index) {
	var addressEditUrl = addressEditModalTarget + index;
	addressLoadEntity(addressEditUrl);
}

function citiesShowEditModal(index) {
	var citiesEditUrl = citiesEditModalTarget + index;
	citiesLoadEntity(citiesEditUrl);
}

function showDeleteModal(index) {
    $('#delete-id').val(index);
}

// Ajax request for Something to populate the modal form.
function busLoadEntity(url) {
    $.getJSON(url, {}, function (data) {
        busPopulateModal(data, busNames);
        console.log("JSON returned");
    });
}

function busTypeLoadEntity(url) {
	$.getJSON(url, {}, function (data) {
		busTypePopulateModal(data, busTypes);
		console.log("JSON returned");
	});
}

function servicesLoadEntity(url) {
	$.getJSON(url, {}, function (data) {
		servicesPopulateModal(data, services);
		console.log("JSON returned");
	});
}

function stationLoadEntity(url) {
	$.getJSON(url, {}, function (data) {
		stationPopulateModal(data, stations);
		console.log("JSON returned");
	});
}

function addressLoadEntity(url) {
	$.getJSON(url, {}, function (data) {
		addressPopulateModal(data, address);
		console.log("JSON returned");
	});
}

function citiesLoadEntity(url) {
	$.getJSON(url, {}, function (data) {
		citiesPopulateModal(data, cities);
		console.log("JSON returned");
	});
}

// Assign the data values to the modal form.
function busPopulateModal(data) {
	$('#update-id').val(data.id);
    $('#update-busNumber').val(data.busNumber);
    $('#update-busCode').val(data.busCode);
    $('#update-busCompany').val(data.busCompany);
    $('#update-maxSeats').val(data.maxSeats);
    $('#update-busType').val(data.busType);
    
}

function busTypePopulateModal(data) {
	$('#update-busTypeid').val(data.id);
	$('#update-type').val(data.type);
	
}

function servicesPopulateModal(data) {
	$('#update-servicesid').val(data.id);
	$('#update-services').val(data.services);
	
}

function stationPopulateModal(data) {
	$('#update-stationid').val(data.id);
	$('#update-stationName').val(data.name);
	$('#update-phoneNumber').val(data.phoneNumber);
	
}

function addressPopulateModal(data) {
	$('#update-addressid').val(data.id);
	$('#update-addressName').val(data.addressName);
	$('#update-cities').val(data.cities);
	
}

function citiesPopulateModal(data) {
	$('#update-citiesid').val(data.id);
	$('#update-name').val(data.name);
	
}

function clearModal() {
	$('#update-id').val(data.id);
    $('#update-busNumber').val(data.busNumber);
    $('#update-busCode').val(data.busCode);
    $('#update-busCompany').val(data.busCompany);
    $('#update-maxSeats').val(data.maxSeats);
    $('#update-busType').val(data.busType);

}

function closeModal(name) {
    $(name).modal('toggle');
}

function clearAndCloseModal(name) {
    clearModal();
    closeModal(name);
}

// POST the edits to Something to the server.
function postEdit() {
    var something = $('#edit-form').serialize();
    var editUrl = url + 'update';
    $.post(editUrl, something, function (data) {
        updateTable(data);
    });
    clearAndCloseModal('#umodal');
}

function deleteEntity(entity) {
    var input = $('#delete-id');
    var url = '/' + entity + '/delete/' + input.val();
    $.post(url, function (data) {
        updateTable(data);
    });
    closeModal('#dmodal');
    input.val('');
}

function updateTable(data) {
    $.ajax({
        dataType: "json",
        url: tableTarget,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'GET',
        success: function (response) {
            $('#table-body').empty();
            $.each(response, function (i, e) {
                var end = e.id + ");'";
                var edit = "'showEditModal(" + end;
                var del = "'showDeleteModal(" + end;
                var row = $('<tr>').append(
                    $('<td>').text(e.id),
                    $('<td>').text(e.busNumber),
                    $('<td>').text(e.busCode),
                    $('<td>').text(e.busCompany),
                    $('<td>').text(e.maxSeats),
                    $('<td>').text(e.takenSeats),
                    $('<td>').text(e.busType),
                    $('<td>').append(
                        "<a data-toggle='modal' data-target='#umodal' onclick=" +
                        edit + ">Edit</a>"
                    ),
                    $('<td>').append(
                        "<a data-toggle='modal' data-target='#dmodal' onclick=" +
                        del + ">Delete</a>"
                    )
                );
                $('#bus-table').append(row);
            });
        }
    });
}


