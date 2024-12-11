import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useAuth } from "../context/AuthContext";

interface LoginForm {
  username: string;
  password: string;
}

export const Login = () => {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [error, setError] = useState("");
  const [form, setForm] = useState<LoginForm>({
    username: "",
    password: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const formData = new URLSearchParams();
      formData.append("username", form.username);
      formData.append("password", form.password);

      await axios.post("http://localhost:8080/login", formData, {
        withCredentials: true,
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        maxRedirects: 0, // Add this
      });

      login();
      navigate("/contacts");
    } catch (err: any) {
      // If we get 302, that means successful login
      if (err.response?.status === 302) {
        login();
        navigate("/contacts");
        return;
      }
      console.error("Login error:", err);
      setError("Invalid credentials");
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col items-center pt-16">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h2 className="text-2xl mb-6 text-center font-semibold">Login</h2>

        {error && (
          <div className="bg-red-50 text-red-600 p-3 rounded mb-4">{error}</div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Username
            </label>
            <input
              type="text"
              value={form.username}
              onChange={(e) => setForm({ ...form, username: e.target.value })}
              className="w-full p-2 border rounded focus:ring-2 focus:ring-green-500 focus:border-green-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Password
            </label>
            <input
              type="password"
              value={form.password}
              onChange={(e) => setForm({ ...form, password: e.target.value })}
              className="w-full p-2 border rounded focus:ring-2 focus:ring-green-500 focus:border-green-500"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-green-600 text-white rounded-md py-2 px-4 hover:bg-green-700"
          >
            Sign In
          </button>
        </form>
      </div>
    </div>
  );
};
