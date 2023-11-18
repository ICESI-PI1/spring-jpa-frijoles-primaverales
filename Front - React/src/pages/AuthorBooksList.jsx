import { useEffect, useState } from "react";
import axios from "../config/axios";
import AuthorBooksTable from "../components/AuthorBooksTable";
import PropTypes from "prop-types";
import { AuthorBooksContext } from "../context/AuthorBooksContext";
import { useParams } from "react-router-dom";

function AuthorBooksList({}) {
    const [bookList, setBookList] = useState([]);
  const { authorId } = useParams();

  const getBooks = async () => {
    try {
      const res = await axios.get(`authors/${authorId}/books`);
      console.log("ahi viene la miel");
      console.log(res.data);
      setBookList(res.data);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getBooks();
  }, []);

  return (
    <>
      AuthorList
      <AuthorBooksTable bookList={bookList}/>
    </>
  );
}

export default AuthorBooksList;
