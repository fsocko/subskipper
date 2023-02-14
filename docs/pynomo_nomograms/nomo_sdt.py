"""
    ex_type1_nomo_1.py

    Simple nomogram of type 1: F1 + F2 + F3 = 0

"""
import sys
import numpy as np

sys.path.insert(0, "..")
# sys.path[:0] = [".."]
from pynomo.nomographer import Nomographer

time_params = {
    'u_min': 0.5,
    'u_max': 100.0,
    'function': lambda u: -np.log(u),
    'title': r'$Time (s)$',
    'tick_levels': 3,
    'tick_text_levels': 2,
    'scale_type': 'log smart',
}

dist_params = {
    'u_min': 0.5,
    'u_max': 100.0,
    'function': lambda u: np.log(u),
    'title': r'$dist (m)$',
    'tick_levels': 3,
    'tick_text_levels': 2,
    'tick_side': 'right',
    'scale_type': 'log smart',
}

speed_params = {
    'u_min': 0.5,
    'u_max': 40.0,
    'function': lambda u: -np.log(u),
    'title': r'$Speed (m/s)$',
    'tick_levels': 3,
    'tick_text_levels': 2,
    'tick_side': 'right',
    'scale_type': 'log smart',
}

block_1_params = {
    'block_type': 'type_1',
    'width': 10.0,
    'height': 10.0,
    'f1_params': time_params,
    'f2_params': dist_params,
    'f3_params': speed_params,
    'isopleth_values': [[5, 50, 'x']],
}

main_params = {
    'filename': 'ex_type1_nomo_1.pdf',
    'paper_height': 12.0,
    'paper_width': 12.0,
    #'mirror_y':True,
    'block_params': [block_1_params],
    'transformations': [('rotate', 0.01), ('scale paper',)],
    'title_str': r'$ -log(Speed) + log(Time) - log(Distance) =0$',
}
Nomographer(main_params)
