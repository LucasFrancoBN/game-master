module.exports = {
  preset: 'jest-preset-angular',
  globalSetup: 'jest-preset-angular/global-setup',
  testEnvironment: 'jsdom',
  moduleNameMapping: {
    '^src/(.*)$': '<rootDir>/src/$1'
  },
  transformIgnorePatterns: [
    'node_modules/(?!.*\\.mjs$|@angular|rxjs)'
  ],
  collectCoverageFrom: [
    'src/app/**/*.ts',
    '!src/app/**/*.module.ts',
    '!src/main.ts',
    '!src/test.ts'
  ],
  coverageDirectory: './coverage',
  coverageReporters: ['html', 'text-summary']
};
