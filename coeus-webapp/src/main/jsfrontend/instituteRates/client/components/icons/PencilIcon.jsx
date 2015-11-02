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

export default class PencilIcon extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <svg {...this.props} version="1.1" x="0px" y="0px" viewBox="0 0 100 125" enable-background="new 0 0 100 100" aria-label="Pencil Icon">
        <path fill={this.props.style.color} d="M77.4,10.8c-1.6-1.6-3.8-2.4-5.9-2.4s-4.3,0.8-5.9,2.4L22.4,54L46,77.6l43.2-43.2c3.3-3.3,3.3-8.5,0-11.8  L77.4,10.8z"/>
        <polygon fill={this.props.style.color} points="39.8,83.2 16.8,60.2 8.3,91.7 "/>
      </svg>
    );
  }
}

PencilIcon.defaultProps = {
  style: {
    color: 'white'
  }
};