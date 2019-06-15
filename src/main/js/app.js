class Employee extends React.Component{
	render() {
		return (
				<INPUT TYPE="Radio" Name="apiRequest" Value="getall">GETALL
				<INPUT TYPE="Radio" Name="apiRequest" Value="get">GET
				<INPUT TYPE="Radio" Name="apiRequest" Value="post">POST
				<INPUT TYPE="Radio" Name="apiRequest" Value="put">PUT
				<INPUT TYPE="Radio" Name="apiRequest" Value="delete">DELETE
				<INPUT TYPE="Radio" Name="apiRequest" Value="deleteall">DELETEALL
                <br /><br />
			<tr>
				<td>{this.props.employee.empId}</td><br />
				<td>{this.props.employee.empName}</td><br />
				<td>{this.props.employee.empSkill}</td><br />
			</tr><br /><br />
			

			<button type="submit" form="form1" value="Submit">Submit</button>

		)
	}
}