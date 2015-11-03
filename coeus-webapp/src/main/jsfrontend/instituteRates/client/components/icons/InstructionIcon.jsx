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

export default class InstructionIcon extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <svg style={this.props.style} enable-background="new 0 0 100 100" viewBox="0 0 100 125" y="0px" x="0px" version="1.0" role="img" aria-labelledby="title">
        <title id="title">Instruction Icon</title>
        <path fill={this.props.style.color} d="M 50.473,0.945 C 23.119,0.945 0.946,23.119 0.946,50.472 0.945,77.824 23.118,100 50.473,100 77.826,100 100,77.824 100,50.473 100,23.119 77.826,0.945 50.473,0.945 Z m 8.546,85.626729 -17.09,0 0,-49.150729 17.089,0 c 0.001,18.325667 -0.0019,30.825062 10e-4,49.150729 z M 50.361,30.676 c -5.396,0 -8.994,-3.823 -8.882,-8.544 -0.112,-4.947 3.485,-8.657 8.994,-8.657 5.509,0 8.994,3.71 9.106,8.657 10e-4,4.721 -3.597,8.544 -9.218,8.544 z" />
      </svg>
    );
  }
}

InstructionIcon.defaultProps = {
  style: {
    color: 'white'
  }
};
