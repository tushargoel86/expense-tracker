import { convertDateFormat } from "../constants/utility/Utility";
import {
  APP_URL,
  EXPENSE_APP_URL,
  USER_APP_URL,
  FETCH_USER,
  ADD_USER,
} from "../constants/utility/Constants";

export const addUser = (data) => {
  return fetch(ADD_USER, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    body: JSON.stringify(data),
  });
};

export const fetchUser = (email) => {
  return fetch(FETCH_USER + encodeURIComponent(email), {
    method: "GET",
    headers: {
      "Access-Control-Allow-Origin": "*",
    },
  });
};

export const addTransaction = (data) => {
  return fetch(EXPENSE_APP_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    body: JSON.stringify(data),
  });
};

export const fetchTransactions = (start, end, uid) => {
  const data = {
    startDate: convertDateFormat(start),
    endDate: convertDateFormat(end, 1),
    userId: uid,
  };

  return fetch(APP_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
};

export const deleteTransaction = (periodId, userId, tagId, transactionid) => {
  return fetch(
    `${APP_URL}/periods/${periodId}/users/${userId}/tags/${tagId}/transactions/${transactionid}`,
    {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
};
