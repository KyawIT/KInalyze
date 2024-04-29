const uidInput = document.getElementById("uidInput");
const generateUID = document.getElementById("generateUID");
const analyzeButton = document.getElementById("analyze");
let dataObj = {};


function toggleSidebar() {
  var sidebar = document.getElementById('sidebar');
  sidebar.classList.toggle('open');
}

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
/*
analyzeButton.addEventListener("click", () => {
  console.log(dataObj);
  sendDataToServer(dataObj, "http://localhost:8080/api/file/analyze");
});*/

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
    })
    .catch((error) => {
      console.error(
        "There was a problem with the POST request:",
        error.message
      );
    });
}

const dropArea = document.getElementById('drop-area');

        dropArea.addEventListener('dragenter', (e) => {
            e.preventDefault();
            dropArea.classList.add('highlight');
        });

        dropArea.addEventListener('dragover', (e) => {
            e.preventDefault();
        });

        dropArea.addEventListener('dragleave', () => {
            dropArea.classList.remove('highlight');
        });

        dropArea.addEventListener('drop', (e) => {
            e.preventDefault();
            dropArea.classList.remove('highlight');

            const files = e.dataTransfer.files;

            if (files.length > 0) {
                handleFiles(files);
            }
        });

        document.getElementById('fileInput').addEventListener('change', (e) => {
            const files = e.target.files;
            handleFiles(files);
        });

        function handleFiles(files) {
          const fileInfo = document.getElementById("fileInfo");
            for (const file of files) {
                  let fileName = "";
                  let fileSize = "";
                  let fileType = "";
                  let fileContent = ""; // Moved declaration here
                
                  if (uidInput.value === "" || uidInput.value === null) {
                    alert("Please enter a UID before uploading a file.");
                    return;
                  }
                
                  const java = file;
                
                  fileInfo.innerHTML = "";
                
                  if (java) {
                    fileName = java.name;
                    fileSize = java.size;
                    fileType = java.type;
                    const reader = new FileReader();
                    reader.onload = function (event) {
                      fileContent = event.target.result; // Assign the file content here
                
                      const fileInfoText = `
                      <div class="codeOutput">
                          <div class="codeOutputHeader">
                          <strong>File Name:</strong> ${fileName}<br>
                          <strong>File Size:</strong> ${fileSize} bytes<br>
                          <strong>File Type:</strong> Java <br>
                          <strong>File Content:</strong>
                          </div>
                          <div class="contentOutput">${fileContent}</div>
                        </div>`;
                
                      fileInfo.innerHTML += fileInfoText;
                
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
                    reader.readAsText(java);
                  } else {
                    fileInfo.textContent = "No file selected.";
                  }
                

            }
        }
