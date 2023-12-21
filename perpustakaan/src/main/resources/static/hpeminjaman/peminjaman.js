

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
	 });
	 
	 $('#tablepeminjaman tbody').on('click', '.btn-edit', function (e) {
		    e.preventDefault();

		    // Mengambil data dari baris yang diklik
		    var data = table.row($(this).parents('tr')).data();

		    // Memasukkan nilai ke dalam textbox
		    $('#idTextbox').val(data.id);
		    $('#namaTextbox').val(data.nama);
		    $('#judulTextbox').val(data.judul);
		    $('#tanggalPeminjamanTextbox').val(data.tanggal_peminjaman);
		    $('#tanggalPengembalianTextbox').val(data.tanggal_pengembalian);
		});

});


$(document).ready(function () {
    $('#submitBtn').on('click', function () {
    	
        // Mendapatkan nilai dari textbox
    	var idValue = $('#idTextbox').val();
	    var namaValue = $('#namaTextbox').val();
	    var judulValue = $('#judulTextbox').val();
	    var tanggalPeminjamanValue = $('#tanggalPeminjamanTextbox').val();
	    var tanggalPengembalianValue = $('#tanggalPengembalianTextbox').val();
	    
        var apiUrl = 'http://localhost:8080/peminjaman/' + idValue;

        // Data yang akan dikirimkan
        var requestData = {
            nama: namaValue,
            judul: judulValue,
            tanggal_peminjaman: tanggalPeminjamanValue,
            tanggal_pengembalian: tanggalPengembalianValue
            // Tambahkan properti lain sesuai kebutuhan
        };

        if (idValue == 0 || idValue == null || idValue == '') {
            apiUrl = 'http://localhost:8080/peminjaman';
            // Permintaan POST menggunakan jQuery
            $.ajax({
                url: apiUrl,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(requestData),
                success: function (data) {
                    console.log('POST request successful', data);
                    // Lakukan sesuatu setelah permintaan berhasil
                    window.location.href = '../front/peminjaman';
                },
                error: function (error) {
                    console.error('Error in POST request', error);
                    // Lakukan sesuatu jika ada kesalahan
                }
            });
        } else {
            // Permintaan PUT menggunakan jQuery
            $.ajax({
                url: apiUrl,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(requestData),
                success: function (data) {
                    console.log('PUT request successful', data);
                    // Lakukan sesuatu setelah permintaan berhasil
                    window.location.href = '../front/peminjaman';
                },
                error: function (error) {
                    console.error('Error in PUT request', error);
                    // Lakukan sesuatu jika ada kesalahan
                }
            });
        }
    });
});

$(document).ready(function () {
	 $('#tablepeminjaman tbody').on('click', '.btn-delete', function () {
	        var idValue = $(this).data('id');

	        // Konfirmasi penghapusan
	        if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
	            var apiUrl = '/peminjaman/test/' + idValue;

	            // Permintaan DELETE menggunakan fetch
	            fetch(apiUrl, {
	                method: 'DELETE',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	            })
	            .then(response => {
	                if (!response.ok) {
	                    throw new Error('Network response was not ok');
	                }
	                return response.json();
	            })
	            .then(data => {
	                console.log('DELETE request successful', data);
	                // Perbarui tabel setelah penghapusan berhasil
	                
	            })
	            .catch(error => {
	                console.error('Error in DELETE request', error);
	            });
	        }
	        window.location.href = '../front/peminjaman';
	    });

});