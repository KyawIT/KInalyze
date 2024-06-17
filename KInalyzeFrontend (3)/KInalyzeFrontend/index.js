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
      const fileInfoText = ``;
      /*const fileInfoText = 
      `
          <p><strong>File Name:</strong> ${fileName}</p>
          <p><strong>File Size:</strong> ${fileSize} bytes</p>
          <p><strong>File Type:</strong> ${fileType}</p>
          <p><strong>File Content:</strong></p>
          <pre>${fileContent}</pre>`;*/

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
      <br>
      <br>
      <h1>
        <span style="color:#fff;">Analyze</span>
        <span style="color:#fff;text-decoration-line: underline;text-decoration-style: dotted; text-decoration-color: #808080;">${data.file_name}</span>
        <span style="color:#fff;">:</span>
      </h1>
      <p style="color:#808080">${data.user_uid}</p>
      <br>
      <p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.bad_inits) === 0 ? '#C9ED82' : parseInt(data.bad_inits) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
      <strong style="color: #000000;>Comments:</strong> 
      <span style="color: #000000;>${data.comments}</span>
      </p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.bad_inits) === 0 ? '#99C66C' : parseInt(data.bad_inits) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:right; color: #fff;">
  <strong style="color: #000000">Bad Initializations:</strong>
  <span style="padding: 5px; color: #000000">${data.bad_inits}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.bad_pracs) === 0 ? '#99C66C' : parseInt(data.bad_pracs) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
  <strong style="color: #000000">Bad Practices:</strong> 
  <span style="padding: 5px; color: #000000">${data.bad_pracs.substring(0, 50) + " ..."}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.complex) === 0 ? '#99C66C' : parseInt(data.complex) <= 0.5 ? '#ffc166' : '#f44'}; width:49%;float:right;">
  <strong style="color: #000000;">Complexity:</strong> 
  <span style="color: #000000;">${data.complex}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.constructs) === 0 ? '#99C66C' : parseInt(data.constructs) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
  <strong style="color: #000000;">Constructs:</strong>
  <span style="color: #000000;">${data.constructs}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.data_type) === 0 ? '#99C66C' : parseInt(data.data_type) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:right;">
  <strong style="color: #000000;">Data Types:</strong> 
  <span style="color: #000000;">${data.data_type}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.indents) === 0 ? '#99C66C' : parseInt(data.indents) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
  <strong style="color: #000000;">Indents:</strong> 
  <span style="color: #000000;">${data.indents}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.linemethodratio) <= 20 ? '#99C66C' : parseInt(data.linemethodratio) <= 30 ? '#ffc166' : '#f44'}; width:49%;float:right;">
  <strong style="color: #000000;">Line Method Ratio:</strong>
  <span style="color: #000000">${data.linemethodratio}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.linemethodratio) === 0 ? '#99C66C' : parseInt(data.linemethodratio) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
  <strong style="color: #000000;">Naming Conventions:</strong> 
  <span style="color: #000000;">${data.naming_convs.substring(0, 20) + " ..."}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${data.performance === 'Good' ? '#99C66C' : data.performance === 'Fair' ? '#ffc166' : '#f44'}; width:49%;float:right;">
  <strong style="color: #000000;">Performance:</strong>
  <span style="color: #000000">${data.performance}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.bad_inits) === 0 ? '#99C66C' : parseInt(data.bad_inits) <= 2 ? '#ffc166' : '#f44'}; width:49%;float:left;">
  <strong style="color: #000000;">Unreadables:</strong> 
  <span style="color: #000000;">${data.unreadables}</span>
</p>
<p style="border: 2px solid #808080; border-radius: 5px; padding:10px;background-color:${parseInt(data.var_ENG) === 100 ? '#99C66C' : parseInt(data.var_ENG) >= 80 ? '#ffc166' : '#f44'}; width:49%;float:right;">
  <strong style="color: #000000">Variable Englisch:</strong>
  <span style="color: #000000;">${data.var_ENG}%</span>
</p>

    
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
