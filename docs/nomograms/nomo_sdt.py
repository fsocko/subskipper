"""
    ex_type1_nomo_1.py

    Simple nomogram of type 1: F1 + F2 + F3 = 0

"""
import sys
import numpy as np

sys.path.insert(0, "..")
# sys.path[:0] = [".."]
from pynomo.nomographer import Nomographer

time_params_s = {
    'tag': 'time',
    'u_min': 1.0,
    'u_max': 60.0,
    'function': lambda u: -np.log(u),
    'title': r'$Time (s)$',
    'tick_levels': 3,
    'tick_text_levels': 2,
    'tick_side': 'left',
    'title_x_shift': -0.8,
    'scale_type': 'log smart',
}

time_params_min = {
    'tag': 'time',
    'u_min': 1.0, # time_params_s.u_min / 60
    'u_max': 180.0, # time_params_s.u_max / 60
    'function': lambda u: -np.log(u),
    'title': r'$Time (min)$',
    'tick_levels': 4,
    'align_func': lambda u: u, #Revise this log, a little too high
    'tick_text_levels': 4,
    'tick_side': 'right',
    'title_x_shift': 0.8,
    'scale_type': 'log smart',
}


dist_params_m = {
    'tag': 'dist',
    'u_min': 1,
    'u_max': 1000.0,
    'function': lambda u: np.log(u),
    'title': r'$dist (m)$',
    'tick_levels': 4,
    'align_func': lambda u: u,
    'tick_text_levels': 4,
    'tick_side': 'left',
    'title_x_shift': -0.8,
    'scale_type': 'log smart',
}

dist_params_km = {
    'tag': 'dist',
    'u_min': 0.5,
    'u_max': 120.0,
    'function': lambda u: np.log(u),
    'title': r'$dist (Km)$',
    'tick_levels': 4,
    'align_func': lambda u: u, #Revise this log, a little too high
    'tick_text_levels': 4,
    'tick_side': 'right',
    'title_x_shift': 0.8,
    'scale_type': 'log smart',
}


speed_params_ms = {
    'tag': 'speed',
    'u_min': 0.5,
    'u_max': 40.0,
    'function': lambda u: -np.log(u),
    'title': r'$Speed (m/s)$',
    'tick_levels': 4,
    'align_func': lambda u: u,
    'tick_text_levels': 4,
    'tick_side': 'left',
    'title_x_shift': -0.8,
    'scale_type': 'log smart',
}

speed_params_kt = {
    'tag': 'speed',
    'u_min': 0.5,
    'u_max': 40.0,
    'function': lambda u: -np.log(u),
    'title': r'$Speed (kt)$',
    'tick_levels': 4,
    'align_func': lambda u: u, #Revise this log, a little too high
    'tick_text_levels': 4,
    'tick_side': 'right',
    'title_x_shift': 0.8,
    'scale_type': 'log smart',
}

block_1_params = {
    'block_type': 'type_1',
    'width': 10.0,
    'height': 10.0,
    'f1_params': time_params_s,
    'f2_params': dist_params_m,
    'f3_params': speed_params_ms,
    'isopleth_values': [[5, 50, 'x']],
}

block_2_params = {
    'block_type': 'type_8',
    'f_params': time_params_min,
    'isopleth_values': [['x']],
}

block_3_params = {
    'block_type': 'type_8',
    'f_params': dist_params_km,
    'isopleth_values': [['x']],
}

block_4_params = {
    'block_type': 'type_8',
    'f_params': speed_params_kt,
    'isopleth_values': [['x']],
}

main_params = {
    'filename': 'sdt_nomo.pdf',
    'paper_height': 18.0,
    'paper_width': 10.0,
    #'mirror_y':True,
    'block_params': [block_1_params, block_2_params, block_3_params, block_4_params],
    'transformations': [('rotate', 0.01), ('scale paper',)],
    'title_str': r'$ -log(Speed) + log(Time) - log(Distance) =0$',
}
Nomographer(main_params)
