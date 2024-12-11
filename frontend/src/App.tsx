// src/App.tsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { Login } from "./components/Login";
import { ContactsList } from "./components/ContactsList";
import { ContactDetails } from "./components/ContactDetails";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/contacts" element={<ContactsList />} />
          <Route path="/contacts/:id" element={<ContactDetails />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}
export default App;
