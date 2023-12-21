

$(document).ready( function () {
	 var table = $('#tablepeminjaman').DataTable({
			"sAjaxSource": "/peminjaman/test",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		      { "mData": "nama" },
				  { "mData": "judul" },
				  { "mData": "tanggal_peminjaman" },
				  { "mData": "tanggal_pengembalian" },
				  { "mData": "denda_keterlambatan" },
				  {
		            	"mRender": function (data, type, full) {
		                    var editButton = '<a class="btn btn-info btn-sm btn-edit" href=#/' + full.id + '>Edit</a>';
		                    var deleteButton = '<button class="btn btn-danger btn-sm btn-delete" data-id="' + full.id + '">Delete</button>';
		                    return editButton + ' ' + deleteButton;
		                }
		            }
			]
	 })
});


