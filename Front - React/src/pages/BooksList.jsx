import { useEffect, useState } from "react";
import axios from "../config/axios";
import BookTable from "../components/BookTable";
import BookForm from "../components/BookForm";
import PropTypes from "prop-types";
import { BookContext } from "../context/BookContext";

function BookList({ owner }) {
  const [bookList, setBookList] = useState([]);
  const [authorList, setAuthorList] = useState([]);
  const [bookEdit, setBookEdit] = useState({
    id: "",
    nationality: "",
    author: "",
  });

  const getBooks = async () => {
    try {
      const res = await axios.get("/books");
      setBookList(res.data);
    } catch (e) {
      console.log(e);
    }
  };

  const getAuthors = async () => {
    try {
      const res = await axios.get("/authors");
      setAuthorList(res.data);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getBooks();
  }, []);

  useEffect(() => {
    getAuthors();
  }, []);

  const addBook = async (book) => {
    if (book.id == "") {
      book.id = Math.floor(Math.random() * 100000);
      try {
        const res = await axios.post("/books", book);
        if (res.status == 201 || res.status == 200) getBooks();
      } catch (e) {
        console.log(e);
      }
    } else {
      try {
        const res = await axios.put("/books/" + book.id, book);
        if (res.status == 200) getBooks();
      } catch (e) {
        console.log(e);
      }
    }
    setBookEdit({ id: "", title: "", author: "" });
  };

  const delBook = async (id) => {
    try {
      const res = await axios.delete("/books/" + id);
      if (res.status == 200) getBooks();
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <BookContext.Provider value={{ bookEdit, setBookEdit }}>
      BookList
      <BookForm authorList={authorList} addBook={addBook} bookEdit={bookEdit} />
      <BookTable bookList={bookList} delBook={delBook} editBook={setBookEdit} />
    </BookContext.Provider>
  );
}

BookList.propTypes = {
  owner: PropTypes.string,
};

export default BookList;
