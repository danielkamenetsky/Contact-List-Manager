import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Login } from "./components/Login";
import { ContactsList } from "./components/ContactsList";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/contacts" element={<ContactsList />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
