import { useEffect, useState } from "react";
import axios from "../config/axios";
import AuthorTable from "../components/AuthorTable";
import AuthorForm from "../components/AuthorForm";
import PropTypes from "prop-types";
import { AuthorContext } from "../context/AuthorContext";

function AuthorList({ owner }) {
  const [authorList, setAuthorList] = useState([]);
  const [authorEdit, setAuthorEdit] = useState({
    id: "",
    name: "",
    nationality: ""
  });

  const getAuthors = async () => {
    try {
      const res = await axios.get("/authors");
      setAuthorList(res.data);
    } catch (e) {
      console.log(e);
    }
  };

  useEffect(() => {
    getAuthors();
  }, []);

  const addAuthor = async (author) => {
    if (author.id == "") {
      author.id = Math.floor(Math.random() * 100000);
      try {
        const res = await axios.post("/authors", author);
        if (res.status == 201 || res.status == 200) getAuthors();
      } catch (e) {
        console.log(e);
      }
    } else {
      try {
        const res = await axios.put("/authors/" + author.id, author);
        if (res.status == 200) getAuthors();
      } catch (e) {
        console.log(e);
      }
    }
    setAuthorEdit({ id: "", name: "", nationality: "" });
  };

  const delAuthor = async (id) => {
    try {
      const res = await axios.delete("/authors/" + id);
      if (res.status == 200) getAuthors();
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <AuthorContext.Provider value={{ authorEdit, setAuthorEdit }}>
      AuthorList
      <AuthorForm addAuthor={addAuthor} authorEdit={authorEdit} />
      <AuthorTable
        authorList={authorList}
        delAuthor={delAuthor}
        editAuthor={setAuthorEdit}
      />
    </AuthorContext.Provider>
  );
}

AuthorList.propTypes = {
  owner: PropTypes.string,
};

export default AuthorList;
