import React, { useState, useCallback } from "react";

import Range from "./dates/Range";
import Tags from "./Tags";
import CardLayout from "../ui/CardLayout";
import PeriodTable from "../ui/table/PeriodTable";

import AddTransaction from "./AddTransaction";
import Report from "./report/Report";
import { fetchTransactions } from "../../actions/Action";
import { convertDateFormat } from "../../constants/utility/Utility";

import { Spinner } from "react-bootstrap";

const Period = (props) => {
  var currentDate = new Date();
  const [start, setStart] = useState(
    convertDateFormat(
      new Date(currentDate.getFullYear(), currentDate.getMonth(), 1)
    )
  );
  const [end, setEnd] = useState(
    convertDateFormat(
      new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0)
    )
  );
  var [data, setData] = useState();
  var [isLoading, setLoading] = useState(false);

  const handlePeriodSubmission = useCallback((startDate, endDate) => {
    setStart(convertDateFormat(startDate));
    setEnd(convertDateFormat(endDate));

    fetchTransactions(startDate, endDate, props.userid)
      .then((res) => res.json())
      .then((res) => {
        setData(res);
        loadSpinner(false);
      })
      .catch((err) => {
        setData();
        loadSpinner(false);
      });
  });

  const fetchDetails = () => handlePeriodSubmission(start, end);
  const loadSpinner = (isLoading) => setLoading(isLoading);

  return (
    <CardLayout styleName={{ marginTop: 20 }} bg="warning">
      <Range
        submitPeriod={handlePeriodSubmission}
        setData={setData}
        userid={props.userid}
        loadSpinner={loadSpinner}
      >
      </Range>
      {data && data.tags && <Report tags={data.tags} />}
      {isLoading && <Spinner animation="border" size="lg" />}
      <AddTransaction
          startDate={start}
          endDate={end}
          userId={props.userid}
          fetchDetails={fetchDetails}
          loadSpinner={loadSpinner}
        />
      <PeriodTable>
        {data && data.tags && (
          <Tags
            tags={data.tags}
            periodId={data.periodId}
            userid={props.userid}
            fetchDetails={fetchDetails}
          />
        )}
      </PeriodTable>
    </CardLayout>
  );
};

export default Period;
