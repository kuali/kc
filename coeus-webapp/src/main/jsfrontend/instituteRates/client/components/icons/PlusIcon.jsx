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

export default class PlusIcon extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <svg {...this.props} version="1.1" x="0px" y="0px" viewBox="5.0 -10.0 100.0 135.0" enable-background="new 0 0 100 100" role="img" aria-label="Plus Icon">
        <line fill={this.props.style.color} stroke={this.props.style.color} strokeWidth="4" strokeLinecap="round" stroke-miterlimit="10" x1="50" y1="5" x2="50" y2="95"/>
        <line fill={this.props.style.color} stroke={this.props.style.color} strokeWidth="4" strokeLinecap="round" stroke-miterlimit="10" x1="95" y1="50" x2="5" y2="50"/>
      </svg>
    );
  }
}

PlusIcon.defaultProps = {
  style: {
    color: 'white'
  }
};
