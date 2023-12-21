//$(document).ready(function () {
//    var table = $('#tablebuku').DataTable({
//        "sAjaxSource": "/buku",
//        "sAjaxDataProp": "",
//        "order": [[0, "asc"]],
//        "aoColumns": [
//            { "mData": "id" },
//            { "mData": "judul" },
//            { "mData": "penulis" },
//            { "mData": "penerbit" },
//            { "mData": "tahun_terbit" },
//            {
//                "mRender": function (data, type, full) {
//                    // Access id property from the full object
//                    return '<a class="btn btn-info btn-sm btn-edit" data-id="' + full.id + '">' + 'Edit' + '</a>';
//                }
//            }
//        ]
//    });
//
//    // Menangani klik pada tombol Edit
//    $('#tablebuku tbody').on('click', '.btn-edit', function (e) {
//        e.preventDefault();
//        var idValue = $(this).data('id');
//        console.log('Id yang diklik: ' + idValue);
//    });
//});

$(document).ready(function () {
    var table = $('#tablebuku').DataTable({
        "sAjaxSource": "/buku",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            { "mData": "id" },
            { "mData": "judul" },
            { "mData": "penulis" },
            { "mData": "penerbit" },
            { "mData": "tahun_terbit" },
            {
            	"mRender": function (data, type, full) {
                    var editButton = '<a class="btn btn-info btn-sm btn-edit" href=#/' + full.id + '>Edit</a>';
                    var deleteButton = '<button class="btn btn-danger btn-sm btn-delete" data-id="' + full.id + '">Delete</button>';
                    return editButton + ' ' + deleteButton;
                }
            }
        ]
    });

    // Menangani klik pada tombol Edit
    $('#tablebuku tbody').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        
        // Mengambil data dari baris yang diklik
        var data = table.row($(this).parents('tr')).data();

        // Memasukkan nilai ke dalam textbox
        $('#idTextbox').val(data.id);
        $('#judulTextbox').val(data.judul);
        $('#penulisTextbox').val(data.penulis);
        $('#penerbitTextbox').val(data.penerbit);
        $('#tahunTerbitTextbox').val(data.tahun_terbit);
    });
});


$(document).ready(function () {

    $('#submitBtn').on('click', function () {
        // Mendapatkan nilai dari textbox
        var idValue = $('#idTextbox').val();
        var judulValue = $('#judulTextbox').val();
        var penulisValue = $('#penulisTextbox').val();
        var penerbitValue = $('#penerbitTextbox').val();
        var tahunTerbitValue = $('#tahunTerbitTextbox').val();
        
        var apiUrl = 'http://localhost:8080/buku/' + idValue;  // Ganti dengan endpoint sesuai dengan kebutuhan Anda

        // Data yang akan dikirimkan
        var requestData = {
            judul: judulValue,
            penulis: penulisValue,
            penerbit: penerbitValue,
            tahun_terbit: tahunTerbitValue
            // Tambahkan properti lain sesuai kebutuhan
        };


        if(idValue == 0 || idValue == null || idValue == ''){
        	apiUrl = 'http://localhost:8080/buku';
        	$.ajax({
                url: apiUrl,
                type: 'POST',
                contentType: 'application/json', // Set header content type jika data dikirimkan dalam format JSON
                data: JSON.stringify(requestData),
                success: function (data) {
                    console.log('PUT request successful', data);
                    // Lakukan sesuatu setelah permintaan berhasil
                    window.location.href = '../front/buku';
                },
                error: function (error) {
                    console.error('Error in PUT request', error);
                    // Lakukan sesuatu jika ada kesalahan
                }
            });	
        	
        }else{
        	
        // URL endpoint yang akan menerima permintaan PUT
        // Permintaan PUT menggunakan jQuery
        $.ajax({
            url: apiUrl,
            type: 'PUT',
            contentType: 'application/json', // Set header content type jika data dikirimkan dalam format JSON
            data: JSON.stringify(requestData),
            success: function (data) {
                console.log('PUT request successful', data);
                // Lakukan sesuatu setelah permintaan berhasil
                window.location.href = '../front/buku';
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
	 $('#tablebuku tbody').on('click', '.btn-delete', function () {
	        var idValue = $(this).data('id');

	        // Konfirmasi penghapusan
	        if (confirm('Apakah Anda yakin ingin menghapus data ini?')) {
	            var apiUrl = '/buku/' + idValue;

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
	        window.location.href = '../front/buku';
	    });

});
