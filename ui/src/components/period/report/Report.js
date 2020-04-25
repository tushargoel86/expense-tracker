import React from "react";
import { Table, Row, Col } from "react-bootstrap";
import { Chart } from "react-google-charts";

export const TableItems = (props) => {
  var items = props.items.map((item, index) => {
    return (
      <tr key={index}>
        <td>{item.tagName}</td>
        <td>{item.tagAmount}</td>
      </tr>
    );
  });
  return items;
};

export const ReportTable = (props) => {
  return (
    <Table striped bordered hover variant="dark" style={{ width: "20%" }}>
      <thead>
        <tr>
          <th>Category</th>
          <th>Amount</th>
        </tr>
      </thead>
      <tbody>{props.children}</tbody>
    </Table>
  );
};

const report = (props) => {
  const tags = props.tags;
  const itemArray = [];

  var items = tags.map((tag) => {
    let { tagName, tagAmount } = tag;
    itemArray.push([tagName, Math.abs(tagAmount)]);
    return { tagName, tagAmount };
  });
  return (
    <Row style={{ margin: "5%" }}>
      <Col md="3" lg="2">
        <ReportTable>
          <TableItems items={items} />
        </ReportTable>
      </Col>
      <Col md="1" lg="1"></Col>
      <Col md="3" lg="2">
        <Chart
          width={"300px"}
          height={"200px"}
          chartType="PieChart"
          loader={<div>Loading Chart</div>}
          data={[["Category", "Amount"], ...itemArray]}
          options={{
            title: "My Expenses",
            is3D: true,
          }}
          rootProps={{ "data-testid": "1" }}
        />
      </Col>
    </Row>
  );
};

export default report;
