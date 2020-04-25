import React, { useState } from "react";
import DatePicker from "react-datepicker";


const TransactionDate = (props) => {
  const [date, setDate] = useState(new Date());

  const handleChange = (date) => {
    props.onChange(date);
    setDate(date);
  }

  return (
    <DatePicker
      selected={date}
      onChange={(date) => handleChange(date)}
      selectsStart
      startDate={date}
      dateFormat="dd-MM-yyyy"
    />
  );
};

export default TransactionDate;
