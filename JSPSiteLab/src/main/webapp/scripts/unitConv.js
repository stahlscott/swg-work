/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function configureDropDownLists(dd1, dd2, dd3) {
    var tempOpts = ['Celsius', 'Fahrenheit', 'Kelvin'];
    var lengthOpts = ['Inch', 'Centimeter', 'Foot'];
    var massOpts = ['Pound', 'Kilogram', 'Ounce' ];
    
    switch (dd1.value) {
        case 'Temperature':
            dd2.options.length = 0;
            dd3.options.length = 0;
            for (var i = 0; i < tempOpts.length; i++) {
                createOption(dd2, tempOpts[i], tempOpts[i]);
                createOption(dd3, tempOpts[i], tempOpts[i]);
            }
            break;
        case 'Length':
            dd2.options.length = 0;
            dd3.options.length = 0;
            for (var i = 0; i < lengthOpts.length; i++) {
                createOption(dd2, lengthOpts[i], lengthOpts[i]);
                createOption(dd3, lengthOpts[i], lengthOpts[i]);
            }
            break;
        case 'Mass':
            dd2.options.length = 0;
            dd3.options.length = 0;
            for (var i = 0; i < massOpts.length; i++) {
                createOption(dd2, massOpts[i], massOpts[i]);
                createOption(dd3, massOpts[i], massOpts[i]);
            }
            break;
        default:
            dd2.options.length = 0;
            dd3.options.length = 0;
            break;
    }
}

function createOption(dd, text, value) {
    var opt = document.createElement('option');
    opt.value = value;
    opt.text = text;
    dd.options.add(opt);
}