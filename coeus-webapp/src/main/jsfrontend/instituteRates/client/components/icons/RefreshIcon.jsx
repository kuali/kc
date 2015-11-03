/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import React from 'react/addons';

export default class RefreshIcon extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <svg {...this.props} version="1.1" id="Layer_1" x="0px" y="0px" viewBox="5.0 -10.0 100.0 135.0" enable-background="new 0 0 100 100" role="img" aria-labelledby="title">
        <title id="title">Refresh Icon</title>
        <g>
          <path fill={this.props.style.color} d="M95.267,61.641L95.267,61.641c-0.022-0.396-0.159-0.737-0.377-1.014c-0.004-0.004-0.008-0.008-0.011-0.013   c-0.076-0.095-0.159-0.181-0.252-0.26c-0.473-0.436-1.135-0.628-1.782-0.484c-0.091,0.016-0.179,0.037-0.266,0.065   c-0.021,0.008-0.043,0.015-0.064,0.023c-0.229,0.085-0.439,0.215-0.623,0.395L75.591,73.186c-2.063,1.623,0.406,4.764,2.469,3.143   l10.986-8.65C82.229,82.732,67.122,92.857,50,92.857c-19.452,0-36.307-13.063-41.355-31.578c-0.689-2.528-4.533-1.48-3.844,1.047   C10.325,82.581,28.705,96.857,50,96.857c18.208,0,34.277-10.442,42.008-26.129l1,12.105c0.216,2.62,4.2,2.293,3.984-0.328   L95.267,61.641z"/>
          <path fill={this.props.style.color} d="M95.199,37.672C89.671,17.404,71.278,3.143,50,3.143c-18.153,0-34.258,10.397-42.008,26.125l-1-12.102   c-0.215-2.62-4.199-2.293-3.984,0.328l1.724,20.851c0.019,0.404,0.158,0.752,0.381,1.033c0,0,0.001,0.001,0.001,0.001   c0.079,0.1,0.167,0.19,0.264,0.272c0.474,0.434,1.137,0.624,1.784,0.479c0.086-0.016,0.17-0.036,0.253-0.063   c0.025-0.009,0.049-0.017,0.074-0.027c0.225-0.083,0.432-0.211,0.614-0.387l16.306-12.838c2.063-1.622-0.406-4.764-2.469-3.142   l-10.987,8.651C17.783,17.228,32.923,7.143,50,7.143c19.439,0,36.302,13.049,41.355,31.576   C92.044,41.247,95.888,40.2,95.199,37.672z"/>
        </g>
      </svg>
    );
  }
}

RefreshIcon.defaultProps = {
  style: {
    color: 'white'
  }
};
