import React from "react";
import { Table } from "react-bootstrap";
 
const periodTable = (props) => {
  return (
    <Table striped bordered hover size="sm" variant="light">
      <thead>
        <tr>
          <th>Date</th>
          <th>Amount</th>
          <th>Description</th>
          <th>Tag</th>
          <th></th>
        </tr>
      </thead>
      <tbody>{props.children}</tbody>
    </Table>
  );
};

export default periodTable;
