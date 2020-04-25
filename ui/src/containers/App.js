import React, { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-datepicker/dist/react-datepicker.css";

import Envelope from "../components/ui/Envelope";
import Period from "../components/period/Period";

import { addUser, fetchUser } from "../actions/Action";

const App = () => {
  const [userId, setuserId] = useState();

  const getUserDetail = (email) => {
    return fetchUser(email).then((res) => res.json());
  };

  useEffect(() => {
    const data = {
      userName: "Tushar Goel",
      email: "tushargoel86@gmail.com",
    };
    if (!userId)
      addUser(data)
        .then((res) => {
          if (res.status === 412) {
            return getUserDetail(data.email);
          }
          res.json();
        })
        .then((res) => {
          setuserId(res.id);
        })
        .catch((err) => console.log(err));
  }, []);

  const period = userId ? <Period userid={userId} /> : <Period />;
  return <Envelope title="Expense Tracker">{period}</Envelope>;
};
export default App;
