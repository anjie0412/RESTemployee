const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employee: []};
	}

	componentDidMount() {
		client({ path: '/GetAll/employees'}).done(response => {
			this.setState({employee: response.entity._embedded.employees});
		});
	}

	render() {
		const employees = this.props.employee.map(employee =>
		<Employee key={employee._links.self.href} employee={employee}/>
	);
	return (
		<table>
			<tbody>
				<tr>
				<td>{this.props.employee.empID}</td>
				<td>{this.props.employee.empName}</td>
				<td>{this.props.employee.empSkills}</td>
				</tr>
				{employees}
			</tbody>
		</table>
		)
	}
}