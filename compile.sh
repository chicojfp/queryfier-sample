git clone https://github.com/chicojfp/querify.git

cd querify
mvn clean package install 

cd ..
git clone https://github.com/chicojfp/queryfier-sample.git

cd queryfier-sample
mvn clean package exec:exec