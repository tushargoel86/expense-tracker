import React, { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import { fetchTransactions } from "../../../actions/Action";

import { Row, Col, Form } from "react-bootstrap";

const Range = React.memo((props) => {
  var currentDate = new Date();
  const [startDate, setStartDate] = useState(
    new Date(currentDate.getFullYear(), currentDate.getMonth(), 1)
  );
  const [endDate] = useState(
    new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0)
  );

  useEffect(() => {
    props.loadSpinner(true);
    fetchTransactions(startDate, endDate, props.userid)
      .then((res) => res.json())
      .then((res) =>{ props.setData(res); props.loadSpinner(false);})
      .catch((err) => {props.setData('');  props.loadSpinner(false);});
  }, []);

  const handleChangeDate = (date) => {
    setStartDate(date);
    props.submitPeriod(
      date.toLocaleDateString(),
      //end date
      new Date(date.getFullYear(), date.getMonth() + 1, 0)
    );
  };

  return (
    <Form className="justify-content-center">
      <Form.Group as={Row}>
        <Form.Label column sm="2">
          From Date:
        </Form.Label>
        <Col sm="3">
          <DatePicker
            selected={startDate}
            onChange={handleChangeDate}
            selectsStart
            startDate={startDate}
            //       endDate={endDate}
            dateFormat="MMM-yyyy"
            peekNextMonth
            showMonthYearPicker
          />
        </Col>
        <Col>{props.children}</Col>
      </Form.Group>
    </Form>
  );
});

export default Range;
