$(document).ready(function(){
    // AGREGANDO CLASE ACTIVE AL PRIMER ENLACE ====================
    $('.category_list .category_item[category="all"]').addClass('ct_item-active');

    // FILTRANDO PRODUCTOS  ============================================
    $('.category_item').click(function(){
        var catProduct = $(this).attr('category');

        // AGREGANDO CLASE ACTIVE AL ENLACE SELECCIONADO
        $('.category_item').removeClass('ct_item-active');
        $(this).addClass('ct_item-active');

        // OCULTANDO Y MOSTRANDO PRODUCTOS =========================
        $('.product-item').css('transform', 'scale(0)');
        function hideProduct(){
            $('.product-item').hide();
        }
        setTimeout(hideProduct, 400);

        function showProduct(){
            var $selectedProducts = $('.product-item[category="'+catProduct+'"]');
            if ($selectedProducts.length > 0) {
                $selectedProducts.show();
                $selectedProducts.css('transform', 'scale(1)');
            } else {
                // Mostrar mensaje si no hay juegos en la categoría seleccionada
                alert('No hay juegos en esta categoría.');
            }
        }
        setTimeout(showProduct, 400);
    });

    // MOSTRANDO TODOS LOS PRODUCTOS =======================
    $('.category_item[category="all"]').click(function(){
        function showAll(){
            $('.product-item').show();
            $('.product-item').css('transform', 'scale(1)');
        }
        setTimeout(showAll, 400);
    });
});
