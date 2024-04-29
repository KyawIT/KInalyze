const uidInput = document.getElementById("uidInput");
const generateUID = document.getElementById("generateUID");
const analyzeButton = document.getElementById("analyze");
let dataObj = {};

document.getElementById("fileInput").addEventListener("change", (event) => {
  let fileName = "";
  let fileSize = "";
  let fileType = "";
  let fileContent = ""; // Moved declaration here

  if (uidInput.value === "" || uidInput.value === null) {
    alert("Please enter a UID before uploading a file.");
    return;
  }

  const file = event.target.files[0];
  const fileInfo = document.getElementById("fileInfo");

  fileInfo.innerHTML = "";

  if (file) {
    fileName = file.name;
    fileSize = file.size;
    fileType = file.type;

    const reader = new FileReader();
    reader.onload = function (event) {
      fileContent = event.target.result; // Assign the file content here

      const fileInfoText = `
          <p><strong>File Name:</strong> ${fileName}</p>
          <p><strong>File Size:</strong> ${fileSize} bytes</p>
          <p><strong>File Type:</strong> ${fileType}</p>
          <p><strong>File Content:</strong></p>
          <pre>${fileContent}</pre>`;

      fileInfo.innerHTML = fileInfoText;

      // Create the data object and send it to the server here
      let data = {
        user_uid: uidInput.value,
        file_name: fileName,
        file_type: fileType,
        file_size: fileSize,
        file_content: fileContent,
        file_lang: fileName.split(".")[1],
      };
      dataObj = data;
      sendDataToServer(data, "http://localhost:8080/api/upload/file");
    };
    reader.readAsText(file);
  } else {
    fileInfo.textContent = "No file selected.";
  }
});

document.getElementById("generateUID").addEventListener("click", () => {
  uidInput.value = generateUUID();
});

function generateUUID() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0,
      v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

analyzeButton.addEventListener("click", () => {
  console.log(dataObj);
  sendDataToServer(dataObj, "http://localhost:8080/api/file/analyze");
});

function sendDataToServer(data, url) {
  console.log(data);
  fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Response from server:", data);
      console.log(data.comments);
      var analyzes = `
      <p><strong>Comments:</strong> ${data.comments}</p>
      <p><strong>Bad Initializations:</strong> ${data.bad_inits}</p>
      <p><strong>Bad Practices:</strong> ${data.bad_pracs}</p>
      <p><strong>Complexity:</strong> ${data.complex}</p>
      <p><strong>Constructs:</strong> ${data.constructs}</p>
      <p><strong>Data Types:</strong> ${data.data_type}</p>
      <p><strong>File Name:</strong> ${data.file_name}</p>
      <p><strong>Indents:</strong> ${data.indents}</p>
      <p><strong>Line Method Ratio:</strong> ${data.linemethodratio}</p>
      <p><strong>Naming Conventions:</strong> ${data.naming_convs}</p>
      <p><strong>Performance:</strong> ${data.performance}</p>
      <p><strong>Unreadables:</strong> ${data.unreadables}</p>
      <p><strong>User UID:</strong> ${data.user_uid}</p>
      <p><strong>Variable Naming:</strong> ${data.var_ENG}</p>
    
      `
      document.getElementById("fileInfo").innerHTML = analyzes;
      /*document.getElementById("fileInfo").innerHTML = data.comments + "<br>" "<p><strong>File Name:</strong> ${fileName}</p>";
      document.getElementById("fileInfo").textContent += data.bad_inits + "\n";
      document.getElementById("fileInfo").textContent += data.bad_pracs+ "\n";
      document.getElementById("fileInfo").textContent += data.complex + "\n";
      document.getElementById("fileInfo").textContent += data.constructs + "\n";
      document.getElementById("fileInfo").textContent += data.data_type + "\n";
      document.getElementById("fileInfo").textContent += data.file_name + "\n";
      document.getElementById("fileInfo").textContent += data.indents + "\n";
      document.getElementById("fileInfo").textContent += data.linemethodratio + "\n";
      document.getElementById("fileInfo").textContent += data.naming_convs + "\n";
      document.getElementById("fileInfo").textContent += data.performance + "\n";
      document.getElementById("fileInfo").textContent += data.unreadables + "\n";
      document.getElementById("fileInfo").textContent += data.user_uid + "\n";
      document.getElementById("fileInfo").textContent += data.var_ENG ;*/

    })
    .catch((error) => {
      console.error(
        "There was a problem with the POST request:",
        error.message
      );
    });
}
